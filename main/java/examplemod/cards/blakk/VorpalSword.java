package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class VorpalSword
        extends CustomCard {
    public static final String ID = "VorpalSword";
    public static final String NAME = "Vorpal Sword";
    public static final String DESCRIPTION = "Deal !D! damage. Add a Vorpal Sword to your discard pile. Ethereal.";
    public static final String IMG_PATH = "img/VorpalSword.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 15;
    private static final int UPGRADE_PLUS_DMG = 5;


    public VorpalSword() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.RARE, CardTarget.ENEMY);
        this.damage=this.baseDamage = ATTACK_DMG;
        this.isEthereal = true;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new VorpalSword(), 1));

    }

    @Override
    public AbstractCard makeCopy() {
        return new VorpalSword();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);

        }
    }
}
