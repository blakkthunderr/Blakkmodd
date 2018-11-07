package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class Sacrifice
        extends CustomCard {
    public static final String ID = "Sacrifice";
    public static final String NAME = "Sacrifice";
    public static final String DESCRIPTION = "Gain 1 Soul. Exhaust a random card in your hand.";
    public static final String IMG_PATH = "img/Sacrifice.png";
    private static final int COST = 1;



    public Sacrifice() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulPower(p, 1), 1));
        if (this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, false));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, true));

        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new Sacrifice();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Gain 1 Soul. Exhaust a card in your hand.";
            this.initializeDescription();
        }
    }
}
