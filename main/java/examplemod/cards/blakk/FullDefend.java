package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;

public class FullDefend
        extends CustomCard {
    public static final String ID = "FullDefend";
    public static final String NAME = "Full Defend";
    public static final String DESCRIPTION = "Gain !B! block. Gain 2 Weak.";
    public static final String IMG_PATH = "img/FullDefend.png";
    private static final int COST = 2;
    private static final int BLOCK_AMT = 5;


    public FullDefend() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 15;
        this.block = this.baseBlock;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, 2, false), 2));


    }


    @Override
    public AbstractCard makeCopy() {
        return new FullDefend();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseBlock = 18;


        }
    }
}
