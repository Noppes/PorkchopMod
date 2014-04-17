package net.scmowns.porkchop.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.scmowns.porkchop.EntityPorkChop;

public class RenderPorkChop extends RendererLivingEntity{

    private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");

	public RenderPorkChop() {
		super(new ModelPorkChop(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return pigTextures;
	}
	@Override
    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4){
    	EntityPorkChop pork = (EntityPorkChop) par1EntityLivingBase;
    	if(!pork.getDancing()){
    		super.rotateCorpse(par1EntityLivingBase, par2, par3, par4);
    	}
    	else{
    		float f = (float) Math.sin(pork.ticksExisted * 0.33f) * 0.05f;

    		GL11.glRotatef(pork.rotationYaw, 0, 1, 0);
    		GL11.glRotatef(90, 1, 0, 0);
    		GL11.glRotatef(360 * f, 0, 1, 0);
    		
    		GL11.glTranslatef(f, -0.7f, -0.9f);
    	}
    }
}