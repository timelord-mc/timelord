package pw.tales.timelord.forge_1_12_2;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import pw.tales.timelord.IWorld;
import pw.tales.timelord.TimeLordCommon;

@Mod.EventBusSubscriber(Side.SERVER)
@Mod(modid = TimeLord.MODID, name = TimeLord.MODID, version = TimeLord.VERSION, acceptableRemoteVersions = "*")
public class TimeLord {
    public static final String MODID = "@@MODID@@";
    public static final String VERSION = "@@VERSION@@";

    static final String SYNC_GAMERULE = "doDaylightSync";
    static final String SYNC_OFFSET = "daylightSyncOffset";

    @SubscribeEvent
    public static void onWorldInit(WorldEvent.Load event) {
        World world = event.getWorld();

        GameRules gameRules = world.getGameRules();
        gameRules.addGameRule(SYNC_OFFSET, Integer.toString(0), GameRules.ValueType.NUMERICAL_VALUE);
        gameRules.addGameRule(SYNC_GAMERULE, Boolean.toString(true), GameRules.ValueType.BOOLEAN_VALUE);
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        IWorld world = new ForgeWorld(event.world);
        TimeLordCommon.instance.worldTick(world);
    }
}
