package examplemod.cards.blakk;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import examplemod.patches.AbstractCardEnum;

public class GraspHeart
        extends CustomCard {
    public static final String ID = "GraspHeart";
    public static final String NAME = "Grasp Heart";
    public static final String DESCRIPTION = "The targeted enemy loses !M! HP.";
    public static final String IMG_PATH = "img/GraspHeart.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 10;
    private static final int UPGRADE_PLUS_DMG = 3;


    public GraspHeart() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.BLAKK_COLOR,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber=this.baseMagicNumber = ATTACK_DMG;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, m, this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new GraspHeart();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_DMG);

        }
    }
}
