package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class Shank
        extends CustomCard {
    public static final String ID = "Shank";
    public static final String NAME = "Shank";
    public static final String DESCRIPTION = "Deal !D! damage. Exhaust.";
    public static final String IMG_PATH = "img/Shank.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 10;
    private static final int UPGRADE_PLUS_DMG = 4;


    public Shank() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.damage=this.baseDamage = ATTACK_DMG;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Shank();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);

        }
    }
}
