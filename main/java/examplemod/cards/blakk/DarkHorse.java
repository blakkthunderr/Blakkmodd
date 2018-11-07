package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class DarkHorse
        extends CustomCard {
    public static final String ID = "DarkHorse";
    public static final String NAME = "Dark Horse";
    public static final String DESCRIPTION = "Draw until you have !M! cards in your hand. Gain [E] for each card drawn. You cannot draw any more cards this turn.";
    public static final String IMG_PATH = "img/DarkHorse.png";
    private static final int COST = 2;



    public DarkHorse() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 5;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;

        for (i = AbstractDungeon.player.hand.size(); i < this.magicNumber; ++i) {
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, 1));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));

        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NoDrawPower(p)));




    }


    @Override
    public AbstractCard makeCopy() {
        return new DarkHorse();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);


        }
    }
}
