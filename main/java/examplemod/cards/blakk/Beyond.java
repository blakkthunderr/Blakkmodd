package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class Beyond
        extends CustomCard {
    public static final String ID = "Beyond";
    public static final String NAME = "Beyond";
    public static final String DESCRIPTION = "Convert your current Souls into Strength.";
    public static final String IMG_PATH = "img/Beyond.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;


    public Beyond() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);


        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));

        }
        if (p.hasPower (SoulPower.POWER_ID)){
            this.magicNumber =  (p.getPower(SoulPower.POWER_ID).amount);
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p,  SoulPower.POWER_ID));


    }


    @Override
    public AbstractCard makeCopy() {
        return new Beyond();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Gain 1 Strength. Convert your current Souls into Strength.";
            this.initializeDescription();

        }
    }
}
