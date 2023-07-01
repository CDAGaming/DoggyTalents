package doggytalents.common.talent;

import doggytalents.api.inferface.AbstractDogEntity;
import doggytalents.api.registry.Talent;
import doggytalents.api.registry.TalentInstance;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;

public class HellHoundTalent extends TalentInstance {

    public HellHoundTalent(Talent talentIn, int levelIn) {
        super(talentIn, levelIn);
    }

    @Override
    public InteractionResultHolder<Integer> setFire(AbstractDogEntity dogIn, int second) {
        return InteractionResultHolder.success(this.level() > 0 ? second / this.level() : second);
    }

    @Override
    public InteractionResult isImmuneToFire(AbstractDogEntity dogIn) {
        return this.level() >= 5 ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }

    @Override
    public InteractionResult isInvulnerableTo(AbstractDogEntity dogIn, DamageSource source) {
        if (source.is(DamageTypes.IN_FIRE) || source.is(DamageTypes.ON_FIRE)) {
            return this.level() >= 5 ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult attackEntityAsMob(AbstractDogEntity dogIn, Entity entity) {
        if (this.level() > 0) {
            entity.setSecondsOnFire(this.level());
            return InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }
}
