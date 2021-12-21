package com.xiaolin.skeletonfix.mixin;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(BowAttackGoal.class)
public abstract class SkeletonFixMixin{

    @Shadow @Final
    private HostileEntity actor;

    @Inject(method = "tick",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/HostileEntity;lookAtEntity(Lnet/minecraft/entity/Entity;FF)V"),
                    locals = LocalCapture.CAPTURE_FAILSOFT)
    public void skeletonFix(CallbackInfo info, LivingEntity livingEntity){
        this.actor.getLookControl().lookAt(livingEntity, 30.0f, 30.0f);
    }



/*

    @Overwrite
    public void tick() {
        LivingEntity livingEntity = actor.getTarget();
        if (livingEntity != null) {
            double d = this.actor.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
            boolean bl = this.actor.getVisibilityCache().canSee(livingEntity);
            boolean bl2 = this.targetSeeingTicker > 0;
            if (bl != bl2) {
                this.targetSeeingTicker = 0;
            }

            if (bl) {
                ++this.targetSeeingTicker;
            } else {
                --this.targetSeeingTicker;
            }

            if (!(d > (double) this.squaredRange) && this.targetSeeingTicker >= 20) {
                this.actor.getNavigation().stop();
                ++this.combatTicks;
            } else {
                this.actor.getNavigation().startMovingTo(livingEntity, this.speed);
                this.combatTicks = -1;
            }

            if (this.combatTicks >= 20) {
                if ((double) this.actor.getRandom().nextFloat() < 0.3D) {
                    this.movingToLeft = !this.movingToLeft;
                }

                if ((double) this.actor.getRandom().nextFloat() < 0.3D) {
                    this.backward = !this.backward;
                }

                this.combatTicks = 0;
            }

            if (this.combatTicks > -1) {
                if (d > (double) (this.squaredRange * 0.75F)) {
                    this.backward = false;
                } else if (d < (double) (this.squaredRange * 0.25F)) {
                    this.backward = true;
                }

                this.actor.getMoveControl().strafeTo(this.backward ? -0.5F : 0.5F, this.movingToLeft ? 0.5F : -0.5F);
                this.actor.lookAtEntity(livingEntity, 30.0F, 30.0F);
            }

            this.actor.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);


            if (this.actor.isUsingItem()) {
                if (!bl && this.targetSeeingTicker < -60) {
                    this.actor.clearActiveItem();
                } else if (bl) {
                    int i = this.actor.getItemUseTime();
                    if (i >= 20) {
                        this.actor.clearActiveItem();
                        ((RangedAttackMob) this.actor).attack(livingEntity, BowItem.getPullProgress(i));
                        this.cooldown = this.attackInterval;
                    }
                }
            } else if (--this.cooldown <= 0 && this.targetSeeingTicker >= -60) {
                this.actor.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(this.actor, Items.BOW));
            }
        }
    }
    */
}
