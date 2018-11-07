//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package examplemod.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;


public class LeakingPower extends AbstractPower
{
    public static final String POWER_ID = "Leaking";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;


    public LeakingPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "Leaking";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("img/LeakingPower.png");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new SoulPower(this.owner, -this.amount), -this.amount));

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Leaking");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
