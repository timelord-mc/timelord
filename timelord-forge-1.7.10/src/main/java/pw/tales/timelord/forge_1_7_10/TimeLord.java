package pw.tales.timelord.forge_1_7_10;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import pw.tales.timelord.IWorld;
import pw.tales.timelord.TimeLordCommon;

@Mod(modid = TimeLord.MODID, name = TimeLord.MODID, version = TimeLord.VERSION, acceptableRemoteVersions = "*")
public class TimeLord {
    public static final String MODID = "@@MODID@@";
    public static final String VERSION = "@@VERSION@@";

    static final String SYNC_GAMERULE = "doDaylightSync";
    static final String SYNC_OFFSET = "daylightSyncOffset";

    @Mod.EventHandler
    public void startServer(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onWorldInit(WorldEvent.Load event) {
        World world = event.world;

        GameRules gameRules = world.getGameRules();
        gameRules.addGameRule(SYNC_OFFSET, Integer.toString(0));
        gameRules.addGameRule(SYNC_GAMERULE, Boolean.toString(true));
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        IWorld world = new ForgeWorld(event.world);
        TimeLordCommon.instance.worldTick(world);
    }
}
