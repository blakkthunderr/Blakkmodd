package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.DisasterPower;
import examplemod.powers.WitchProtectionPower;

public class Disaster
        extends CustomCard {
    public static final String ID = "Disaster";
    public static final String NAME = "Disaster";
    public static final String DESCRIPTION = "Whenever you draw a Status card, gain [E] and draw a card.";
    public static final String IMG_PATH = "img/Disaster.png";
    private static final int COST = 3;



    public Disaster() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DisasterPower(p, 1), 1));



    }

    @Override
    public AbstractCard makeCopy() {
        return new Disaster();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);

        }
    }
}
