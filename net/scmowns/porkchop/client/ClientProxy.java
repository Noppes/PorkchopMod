package net.scmowns.porkchop.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.scmowns.porkchop.CommonProxy;
import net.scmowns.porkchop.EntityPorkChop;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	public void load(){
		RenderingRegistry.registerEntityRenderingHandler(EntityPorkChop.class, new RenderPorkChop());
	}
	@Override
	public void spawnParticles(String particles, EntityLivingBase entity) {
		World worldObj = entity.worldObj;
		if(particles.equals("fart")){
            float ff1 = entity.renderYawOffset * (float)Math.PI / 180.0F;
            float ff2 = MathHelper.sin(ff1);
            float ff3 = -MathHelper.cos(ff1);   
            
            double x = entity.posX + ff2 * 0.65;
            double y = (entity.posY + entity.height * 0.8);
            double z = entity.posZ + ff3 * 0.65;
            
	    	for(int i = 0; i < 19; i++){	         
	            double f = (worldObj.rand.nextDouble()) * ff2;
	            double f1 =  worldObj.rand.nextDouble() - 0.5f;
	            double f2 = (worldObj.rand.nextDouble()) * ff3;
	            
	            EntityFartFX fx = new EntityFartFX(worldObj, x, y, z, 0, 0, 0);
	            fx.motionX = f * 0.05f;
	            fx.motionY = f1 * 0.05;
	            fx.motionZ = f2 * 0.05f;
	            
	            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	    	}
		}
	}
	
	ISound lastSound;

	@Override
	public void stopSound(){
		if(lastSound != null)
			Minecraft.getMinecraft().getSoundHandler().stopSound(lastSound);
	}
	public void playSound(String sound, Entity entity){
		Minecraft.getMinecraft().getSoundHandler().stopSounds();
		ResourceLocation playingResource = new ResourceLocation(sound);
		lastSound = PositionedSoundRecord.func_147675_a(playingResource, (float)entity.posX, (float)entity.posY, (float)entity.posZ);

		Minecraft.getMinecraft().getSoundHandler().playSound(lastSound);
	}
}
