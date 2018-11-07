package examplemod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import examplemod.powers.SoulPower;

import java.util.Iterator;


public class VileSoul extends AbstractRelic {
    public static final String ID = "VileSoul";


    public VileSoul() {
        super("VileSoul", "VileSoul.png", RelicTier.BOSS, LandingSound.HEAVY);
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

            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flash();
                Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

                while (var3.hasNext()) {
                    AbstractMonster monster = (AbstractMonster) var3.next();
                    if (!monster.isDead && !monster.isDying) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new PoisonPower(monster, AbstractDungeon.player, count), count));
                    }
                }
            }
        }

    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new VileSoul();
    }


}
