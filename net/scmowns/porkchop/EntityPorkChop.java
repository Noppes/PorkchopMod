package net.scmowns.porkchop;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import net.scmowns.porkchop.ai.EntityAIPorkChopDancing;
import net.scmowns.porkchop.ai.EntityAIPorkChopPanic;

public class EntityPorkChop extends EntityTameable{
	private boolean wasDancing = false;
	
	public EntityPorkChop(World par1World) {
		super(par1World);
        this.setSize(0.9F, 0.9F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPorkChopPanic(this, 1.2D));
        this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 6.0F, 2.0F));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(4, new EntityAIPorkChopDancing(this));
        this.tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(14, (byte)0); // dancing
		
	}
	@Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.45);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}

	@Override
    public void playLivingSound(){
    	if(!getDancing() && getRNG().nextInt(6) == 1)
    		super.playLivingSound();
    }

    public boolean getDancing() {
		return dataWatcher.getWatchableObjectByte(14) == 1;
	}

	public void setDancing(boolean b) {
		dataWatcher.updateObject(14, (byte)(b?1:0));
	}

	@Override
	public void onEntityUpdate(){
		if(worldObj.isRemote){
			boolean isDancing = getDancing();
			if(wasDancing && !isDancing){
				PorkChop.proxy.stopSound();
			}
			else if(!wasDancing && isDancing)
				PorkChop.proxy.playSound("porkchop:misc.dance", this);
			wasDancing = getDancing();
		}
        super.onEntityUpdate();
    }
	@Override
    public boolean getAlwaysRenderNameTag() {
        return true; 
    }

	@Override
    public boolean isAIEnabled() {
        return true;
    }

	@Override
    protected String getHurtSound() {
        return "porkchop:hurt.hurt";
    }

	@Override
    protected String getLivingSound(){
        return "porkchop:living.living";
    }
	@Override
    protected String getDeathSound() {
        return "porkchop:death.death";
    }
}
