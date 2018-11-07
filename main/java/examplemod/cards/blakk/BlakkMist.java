package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.BlakkMistPower;
import examplemod.powers.WitchProtectionPower;

public class BlakkMist
        extends CustomCard {
    public static final String ID = "BlakkMist";
    public static final String NAME = "Blakk Mist";
    public static final String DESCRIPTION = "Your Weak, Vulnerable, and Frail no longer wear off.";
    public static final String IMG_PATH = "img/BlakkMist.png";
    private static final int COST = 1;



    public BlakkMist() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlakkMistPower(p, 1), 1));



    }

    @Override
    public AbstractCard makeCopy() {
        return new BlakkMist();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
