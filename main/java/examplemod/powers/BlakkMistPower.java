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


public class BlakkMistPower extends AbstractPower
{
    public static final String POWER_ID = "BlakkMist";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;


    public BlakkMistPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "BlakkMist";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("img/SoulPower.png");

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        this.flash();
        int count = 0;

        if (this.owner.hasPower(WeakPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new WeakPower(this.owner, 1, false), 1));

        }
        if (this.owner.hasPower(FrailPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new FrailPower(this.owner, 1, false), 1));

        }
        if (this.owner.hasPower(VulnerablePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new VulnerablePower(this.owner, 1, false), 1));

        }

    }



    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("BlakkMist");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
