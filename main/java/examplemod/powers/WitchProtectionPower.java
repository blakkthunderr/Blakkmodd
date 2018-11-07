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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;


public class WitchProtectionPower extends AbstractPower
{
    public static final String POWER_ID = "WitchProtection";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;


    public WitchProtectionPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "WitchProtection";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("img/witchprotectionpower.png");

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        this.flash();
        int count = 0;

        if (this.owner.hasPower(WeakPower.POWER_ID)) {
            count += (this.owner.getPower(WeakPower.POWER_ID).amount);
        }
        if (this.owner.hasPower(FrailPower.POWER_ID)) {
            count += (this.owner.getPower(FrailPower.POWER_ID).amount);
        }
        if (this.owner.hasPower(VulnerablePower.POWER_ID)) {
            count += (this.owner.getPower(VulnerablePower.POWER_ID).amount);
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount*count));

    }



    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("WitchProtection");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
