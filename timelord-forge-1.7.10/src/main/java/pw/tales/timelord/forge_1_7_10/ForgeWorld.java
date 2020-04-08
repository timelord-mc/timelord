package pw.tales.timelord.forge_1_7_10;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import pw.tales.timelord.IWorld;

public class ForgeWorld implements IWorld {
    private World world;
    private GameRules rules;

    public ForgeWorld(World world) {
        this.world = world;
        this.rules = world.getGameRules();
    }

    @Override
    public void setTime(int tick) {
        world.setWorldTime(tick);
    }

    @Override
    public boolean shouldSync() {
        return rules.getGameRuleBooleanValue(TimeLord.SYNC_GAMERULE);
    }

    @Override
    public int getSyncOffset() {
        try {
            return Integer.parseInt(rules.getGameRuleStringValue(TimeLord.SYNC_OFFSET));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void disableDefaultTimeCycle() {
        rules.setOrCreateGameRule("doDaylightCycle", "false");
    }
}
