package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import examplemod.patches.AbstractCardEnum;

public class TableFlip
        extends CustomCard {
    public static final String ID = "TableFlip";
    public static final String NAME = "Table Flip";
    public static final String DESCRIPTION = "Deal !D! damage. If you are Vulnerable, gain !B! Block.";
    public static final String IMG_PATH = "img/TableFlip.png";
    private static final int COST = 1;
    //private static final int BLOCK_AMT = 6;


    public TableFlip() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.baseBlock = 7;
        this.damage=this.baseDamage;
        this.block=this.baseBlock;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if (p.hasPower(VulnerablePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

        }

    }


    @Override
    public AbstractCard makeCopy() {
        return new TableFlip();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseDamage = 10;
            this.baseBlock = 10;

        }
    }
}
