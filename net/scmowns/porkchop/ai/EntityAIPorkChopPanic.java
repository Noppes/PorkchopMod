package net.scmowns.porkchop.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIPanic;

public class EntityAIPorkChopPanic extends EntityAIPanic{

    private EntityCreature theEntityCreature;
	public EntityAIPorkChopPanic(EntityCreature par1EntityCreature, double par2) {
		super(par1EntityCreature, par2);
		
		this.theEntityCreature = par1EntityCreature;
	}
    public void resetTask() {
		if(theEntityCreature.func_142015_aE() + 200 < theEntityCreature.ticksExisted){
			theEntityCreature.setRevengeTarget(null);
		}
    }
}
