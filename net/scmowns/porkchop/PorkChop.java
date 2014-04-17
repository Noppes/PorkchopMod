package net.scmowns.porkchop;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "porkchop", name = "PorkChop", version = "1.7.2")
public class PorkChop {

	@SidedProxy(clientSide = "net.scmowns.porkchop.client.ClientProxy", serverSide = "net.scmowns.porkchops.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item TrailMix;

	@EventHandler
	public void load(FMLPreInitializationEvent ev) {
		TrailMix = new ItemFood(2, 0.1F, false).setUnlocalizedName("TrailMix").setTextureName("porkchop:trailmix").setCreativeTab(CreativeTabs.tabFood);
		GameRegistry.registerItem(TrailMix, "TrailMix");
		
		GameRegistry.addShapelessRecipe(new ItemStack(TrailMix, 3, 0), Items.wheat_seeds, Items.pumpkin_seeds, Items.melon_seeds, Items.sugar);
		proxy.load();
		registerEntity(EntityPorkChop.class, "PorkChop");
		MinecraftForge.EVENT_BUS.register(new EventHandlerPorkChop());
	}
	
	public void registerEntity(Class<? extends Entity> clss, String name){
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(clss, name, id);
		EntityRegistry.registerModEntity(clss, name, id, this, 80, 1, true);
	}
}
