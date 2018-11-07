package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class Backflap
        extends CustomCard {
    public static final String ID = "Backflap";
    public static final String NAME = "Backflap";
    public static final String DESCRIPTION = "Gain !B! Block. Draw !M! card.";
    public static final String IMG_PATH = "img/Backflap.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;


    public Backflap() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = 8;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));


    }


    @Override
    public AbstractCard makeCopy() {
        return new Backflap();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = "Gain !B! Block. Draw !M! cards.";
            this.initializeDescription();

        }
    }
}
