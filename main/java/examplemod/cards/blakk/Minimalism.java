package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.GlassSkinPower;
import examplemod.powers.MinimalismPower;

public class Minimalism
        extends CustomCard {
    public static final String ID = "Minimalism";
    public static final String NAME = "Minimalism";
    public static final String DESCRIPTION = "At the start of your turn, Exhaust 1 card and gain 1 [E].";
    public static final String IMG_PATH = "img/Minimalism.png";
    private static final int COST = 1;



    public Minimalism() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MinimalismPower(p, 1), 1));



    }

    @Override
    public AbstractCard makeCopy() {
        return new Minimalism();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);

        }
    }
}
