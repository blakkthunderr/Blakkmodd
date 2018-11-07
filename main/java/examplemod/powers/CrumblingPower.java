//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package examplemod.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class CrumblingPower extends AbstractPower
{
    public static final String POWER_ID = "Crumbling";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;


    public CrumblingPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "Crumbling";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("img/CrumblingPower.png");
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -damageAmount), -damageAmount));


        this.flash();
        return damageAmount;
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Crumbling");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
