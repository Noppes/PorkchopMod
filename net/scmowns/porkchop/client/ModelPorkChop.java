package net.scmowns.porkchop.client;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.entity.Entity;
import net.scmowns.porkchop.EntityPorkChop;

public class ModelPorkChop extends ModelQuadruped
{
    public ModelPorkChop()
    {
        this(0.0F);
    }

    public ModelPorkChop(float par1)
    {
        super(6, par1);
        this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, par1);
        this.field_78145_g = 4.0F;
    }
    
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
    	EntityPorkChop pork = (EntityPorkChop) par7Entity;
    	if(!pork.getDancing()){
            this.leg1.rotateAngleY = this.leg2.rotateAngleY = this.leg3.rotateAngleY = this.leg4.rotateAngleY = 0;
            this.leg1.rotateAngleZ = this.leg2.rotateAngleZ = this.leg3.rotateAngleZ = this.leg4.rotateAngleZ = 0;
            
            this.leg1.setRotationPoint(-3.0F, 18, 7.0F);
            this.leg2.setRotationPoint(3.0F, 18, 7.0F);
            this.leg3.setRotationPoint(-3.0F, 18, -5.0F);
            this.leg4.setRotationPoint(3.0F, 18, -5.0F);
    		this.head.rotationPointZ = -6;

    		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    	}
    	else{
    		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    		float f = (float) Math.sin(pork.ticksExisted * 0.33f) * 0.24f;

    		this.leg1.rotateAngleX = (float) (Math.PI / 2);
    		this.leg1.rotateAngleY = (float) (Math.PI / 2) * f;
    		this.leg1.rotateAngleZ = (float) (Math.PI / 2) * f;
    		this.leg1.rotationPointY = 14f;
    		this.leg1.rotationPointZ = 7.5f;
    		this.leg2.rotateAngleX = (float) (Math.PI / 2);
    		this.leg2.rotateAngleY = (float) (Math.PI / 2) * f;
    		this.leg2.rotateAngleZ = (float) (Math.PI / 3) * f;
    		this.leg2.rotationPointY = 14f;
    		this.leg2.rotationPointZ = 7.5f;
    		this.leg3.rotateAngleZ = (float) (Math.PI / 2);
    		this.leg3.rotateAngleY = (float) (Math.PI / 4) * f;
    		this.leg3.rotateAngleX = (float) (Math.PI / 2) * f;
    		this.leg3.rotationPointX = -4;
    		this.leg3.rotationPointY = 14f;
    		this.leg3.rotationPointZ = -4;
    		this.leg4.rotateAngleZ = (float) (Math.PI / -2);
    		this.leg4.rotateAngleY = (float) (Math.PI / -4) * f;
    		this.leg4.rotateAngleX = (float) (Math.PI / 2) * f;
    		this.leg4.rotationPointX = 4;
    		this.leg4.rotationPointY = 14f;
    		this.leg4.rotationPointZ = -4;
    		
    		this.head.rotateAngleX = (float) (Math.PI / 2);
    		this.head.rotateAngleY = 0;
    		this.head.rotationPointZ = -10;
    	}
    }
}
