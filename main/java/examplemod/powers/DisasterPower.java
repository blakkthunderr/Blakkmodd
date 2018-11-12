//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package examplemod.powers;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class DisasterPower extends AbstractPower
{
    public static final String POWER_ID = "Disaster";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;


    public DisasterPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = "Disaster";
        this.owner = owner;
        this.amount = newAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("img/DisasterPower.png");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Disaster");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
