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
    
}
