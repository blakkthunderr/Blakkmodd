package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.actions.EqualizerAction;
import examplemod.actions.MakeCardInHandExhaustAction;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.GlassSkinPower;

public class Equalizer
        extends CustomCard {
    public static final String ID = "Equalizer";
    public static final String NAME = "Equalizer";
    public static final String DESCRIPTION = "Make all of the cards in your draw and discard piles cost 1 [E] for the rest of this combat.";
    public static final String IMG_PATH = "img/Equalizer.png";
    private static final int COST = 3;



    public Equalizer() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new EqualizerAction());



    }

    @Override
    public AbstractCard makeCopy() {
        return new Equalizer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = "Innate. Make all of the cards in your draw and discard piles cost 1 [E] for the rest of this combat.";
            this.initializeDescription();

        }
    }
}
