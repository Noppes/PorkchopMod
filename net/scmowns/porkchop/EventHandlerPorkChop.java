package net.scmowns.porkchop;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerPorkChop {
	private Random rand = new Random();
	
	@SubscribeEvent
	public void playerInteract(EntityInteractEvent event){
		ItemStack item = event.entityPlayer.getCurrentEquippedItem();
		if(item == null || item.getItem() != PorkChop.TrailMix || event.target == null || !(event.target instanceof EntityPig))
			return;
		item.damageItem(1, event.entityPlayer);
		EntityPig pig = (EntityPig) event.target;
		
		if(!event.entityPlayer.worldObj.isRemote && rand.nextInt(8) == 1){
			pig.setDead();
			EntityPorkChop porkchop = new EntityPorkChop(event.target.worldObj);
			porkchop.setPosition(pig.posX, pig.posY, pig.posZ);
			porkchop.rotationYaw = pig.rotationYaw;
			porkchop.prevRotationYaw = pig.prevRotationYaw;
			porkchop.rotationPitch = pig.rotationPitch;
			porkchop.prevRotationPitch = pig.prevRotationPitch;
			porkchop.rotationYawHead = pig.rotationYawHead;
			porkchop.prevRotationYawHead = pig.prevRotationYawHead;
			porkchop.setOwner(event.entityPlayer.getCommandSenderName());
			
			event.entityPlayer.worldObj.spawnEntityInWorld(porkchop);
		}
		else{
			pig.worldObj.playSound(pig.posX, pig.posY, pig.posZ, "porkchop:misc.fart", 1, rand.nextFloat() / 2 + 0.4f, false);
			if(event.entityPlayer.worldObj.isRemote)
			PorkChop.proxy.spawnParticles("fart", (EntityLivingBase) event.target);
		}
	}
}
