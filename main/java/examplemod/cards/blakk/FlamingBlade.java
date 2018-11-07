package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class FlamingBlade
        extends CustomCard {
    public static final String ID = "FlamingBlade";
    public static final String NAME = "Flaming Blade";
    public static final String DESCRIPTION = "Deal !D! damage. Add 2 Burns to your discard pile.";
    public static final String IMG_PATH = "img/FlamingBlade.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 25;
    private static final int UPGRADE_PLUS_DMG = 10;


    public FlamingBlade() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage=this.baseDamage = ATTACK_DMG;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Burn(), 2));

    }

    @Override
    public AbstractCard makeCopy() {
        return new FlamingBlade();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);

        }
    }
}
