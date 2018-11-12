package examplemod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WeightedLegbands
        extends CustomCard {
    public static final String ID = "WeightedLegbands";
    public static final String NAME = "Weighted Legbands";
    public static final String DESCRIPTION = "When drawn, permanently increase this card's Dexterity gain by 1. When played, gain !M! Dexterity and reduce this card's Dexterity gain by 1. Exhaust. Ethereal.";
    public static final String IMG_PATH = "img/WeightedLegbands.png";
    private static final int COST = 1;



    public WeightedLegbands() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, CardColor.GREEN,
                CardRarity.UNCOMMON, CardTarget.SELF);


        this.misc = 0;
        this.magicNumber = this.baseMagicNumber = misc;
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {

        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.misc, 1));


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.misc), this.misc));

        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.misc, -1));


    }

    @Override
    public AbstractCard makeCopy() {
        return new WeightedLegbands();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);


        }
    }
}
