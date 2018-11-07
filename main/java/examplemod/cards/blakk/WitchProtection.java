package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.GlassSkinPower;
import examplemod.powers.WitchProtectionPower;

public class WitchProtection
        extends CustomCard {
    public static final String ID = "WitchProtection";
    public static final String NAME = "Witch's Protection";
    public static final String DESCRIPTION = "At the start of your turn, Gain Block equal to the amount of Weak, Vulnerable, and Frail you have.";
    public static final String IMG_PATH = "img/WitchProtection.png";
    private static final int COST = 2;



    public WitchProtection() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WitchProtectionPower(p, 1), 1));



    }

    @Override
    public AbstractCard makeCopy() {
        return new WitchProtection();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);

        }
    }
}
