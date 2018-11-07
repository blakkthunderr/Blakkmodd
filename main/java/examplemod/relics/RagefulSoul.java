package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import examplemod.powers.SoulPower;


public class RagefulSoul extends AbstractRelic {
    public static final String ID = "RagefulSoul";


    public RagefulSoul() {
        super("RagefulSoul", "RagefulSoul.png", RelicTier.BOSS, LandingSound.HEAVY);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }



    @Override
    public void onPlayerEndTurn() {
        this.flash();

        if (AbstractDungeon.player.hasPower(SoulPower.POWER_ID)) {
            int count = (AbstractDungeon.player.getPower(SoulPower.POWER_ID).amount);

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, count), count));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, SoulPower.POWER_ID));

        }

    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RagefulSoul();
    }


}
