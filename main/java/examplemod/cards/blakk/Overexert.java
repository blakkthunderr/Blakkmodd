package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class Overexert
        extends CustomCard {
    public static final String ID = "Overexert";
    public static final String NAME = "Overexert";
    public static final String DESCRIPTION = "Gain 3 Strength. Next turn, Lose 4 Strength.";
    public static final String IMG_PATH = "img/Overexert.png";
    private static final int COST = 0;
    private static final int BLOCK_AMT = 5;


    public Overexert() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);


        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber+1), this.magicNumber+1));


    }


    @Override
    public AbstractCard makeCopy() {
        return new Overexert();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = "Gain 5 Strength. Next turn, Lose 6 Strength.";
            this.initializeDescription();

        }
    }
}
