package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class Revert
        extends CustomCard {
    public static final String ID = "Revert";
    public static final String NAME = "Revert";
    public static final String DESCRIPTION = "Shuffle your hand into your Draw Pile.";
    public static final String IMG_PATH = "img/Revert.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;


    public Revert() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new PutOnDeckAction(p, p, 10, true));


    }


    @Override
    public AbstractCard makeCopy() {
        return new Revert();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
