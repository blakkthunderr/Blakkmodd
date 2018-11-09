package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;

public class ReverseShield
        extends CustomCard {
    public static final String ID = "ReverseShield";
    public static final String NAME = "Reverse Shield";
    public static final String DESCRIPTION = "Gain !B! block for every stack of Frail you have+1.";
    public static final String IMG_PATH = "img/ReverseShield.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 6;


    public ReverseShield() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 5;

        this.magicNumber = 0;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            int i;

            if (p.hasPower(FrailPower.POWER_ID)) {
                this.magicNumber = (p.getPower(FrailPower.POWER_ID).amount)+1;
            } else {
                this.magicNumber = 1;
            }

                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block*this.magicNumber));



    }


    @Override
    public AbstractCard makeCopy() {
        return new ReverseShield();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 8;
            this.rawDescription = "Gain !B! block for every stack of Frail you have.";
            this.initializeDescription();

        }
    }
}
