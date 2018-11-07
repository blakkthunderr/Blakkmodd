package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import examplemod.patches.AbstractCardEnum;
import examplemod.powers.SoulPower;

public class SoulConsume
        extends CustomCard {
    public static final String ID = "SoulConsume";
    public static final String NAME = "Soul Consume";
    public static final String DESCRIPTION = "Lose 1 Soul. Gain 4 Max HP. Exhaust.";
    public static final String IMG_PATH = "img/SoulConsume.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 6;


    public SoulConsume() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.baseHeal = 4;
        this.heal = this.baseHeal;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower (SoulPower.POWER_ID)) {
            this.magicNumber = (p.getPower(SoulPower.POWER_ID).amount);
            if (this.magicNumber >= 1) {

                AbstractDungeon.player.increaseMaxHp(this.baseHeal, false);
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, SoulPower.POWER_ID, 1));

            }

        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new SoulConsume();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseHeal = 6;
            this.rawDescription = "Lose 1 Soul. Gain 6 Max HP. Exhaust.";


            this.initializeDescription();

        }
    }
}
