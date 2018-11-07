package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class Teleport
        extends CustomCard {
    public static final String ID = "Teleport";
    public static final String NAME = "Teleport";
    public static final String DESCRIPTION = "Put a card from your hand into your Discard Pile. Put a card from your Draw Pile into your hand.";
    public static final String IMG_PATH = "img/Teleport.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;


    public Teleport() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 1, false));
        AbstractDungeon.actionManager.addToBottom(new SeekAction(1));


    }


    @Override
    public AbstractCard makeCopy() {
        return new Teleport();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
