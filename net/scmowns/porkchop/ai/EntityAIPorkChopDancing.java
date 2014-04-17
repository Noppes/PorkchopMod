package net.scmowns.porkchop.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.scmowns.porkchop.EntityPorkChop;

public class EntityAIPorkChopDancing extends EntityAIBase{

    private EntityPorkChop pork;
    private int posX, posZ;
	public EntityAIPorkChopDancing(EntityPorkChop par1EntityCreature) {
		this.pork = par1EntityCreature;
	}
	@Override
	public boolean shouldExecute() {
		EntityPlayer player = (EntityPlayer) pork.getOwner();
		if(player == null || player.ticksExisted % 20 != 0)
			return false;
		int x = MathHelper.floor_double(player.posX);
		int z = MathHelper.floor_double(player.posZ);
		if(x == posX && z == posZ && player.getRNG().nextInt(12) == 0)
			return true;
		
		posX = x;
		posZ = z;
		return false;
	}
	@Override
    public boolean continueExecuting(){
		EntityPlayer player = (EntityPlayer) pork.getOwner();
		if(player == null)
			return false;
		int x = MathHelper.floor_double(player.posX);
		int z = MathHelper.floor_double(player.posZ);
		if(x == posX && z == posZ || player.isSneaking()){
			return true;
		}
		
		return false;
    }
	@Override
    public void startExecuting() {
    	pork.setDancing(true);
   
    }
	@Override
    public void resetTask() {
    	pork.setDancing(false);
    }
}
