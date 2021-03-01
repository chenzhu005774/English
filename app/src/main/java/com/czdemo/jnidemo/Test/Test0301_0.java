package com.czdemo.jnidemo.Test;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class Test0301_0 {
    /**
       When a new movement in art 《1.attains》 a 《2.certain》 fashion, it is 《3.advisable》 to find out what its 《4.advocates》 are aiming at,
     for, however farfetched and unreasonable their 《6.principles》 may seem today, it is possible that in years to come they may be 《regarded》 as normal.
     With regard to Futurist poetry, however, the case is rather difficulty for whatever Futurist poetry may be—even admitting
     that the theory on which it is based may be right—it can hardly be 《classed as Literature》.
     当一个新的时刻在艺术上《1.获得;达到》一个《2.必然的》潮流，这是《3.明智的》可以查出《4.拥护者》瞄准的是什么。
     对次，然而 《5.不可及》的和不合理的是这些《6.原则》在今天看来，在未来也许会《视为；尊敬;对....》普通一样是可能的。
     伴随着对未来诗歌,这个情况是比未来诗歌被每个人接受要困难的。即使那是正确的也很难被《归入文学类》
     **/

    /**  This, in 《1.brief》 ,is what the Futurist says: for a century, past 《2.conditions》 of life have been conditionally speeding up,
     till now we live in a world of noise and 《4.violence》 and speed. Consequently, our feelings, thoughts and 《emotions》
     have 《undergone》 a 《5.corresponding》 change. This speeding up of life, says the Futurist, requires a new form of expression.
     We must speed up our literature too, if we want to interpret modem stress. We must pour out a large stream of essential words,
     unhampered by stops, or qualifying adjectives, or finite verbs. Instead of describing sounds we must make up words that imitate them;
     we must use many sizes of type and different colored inks on the same page, and shorten or lengthen words at will。
     简单(《1.简短的》)来说,未来主义说:过去的一个世纪,过去的生活《2.条件；candidate:候选人》一直是有条件快速提高的,
     《3.朝着》现在我们生活的吵杂和《4.暴力》和高速的世界。因此，我们感觉，我思考并且《情绪》
     已经《经历》《5.相应的》改变。这种生活加速,未来主义者说，需要一风格新的《6.表达方式》。

     **/

    private void showAlertDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(null);
        builder.setTitle("提示");
        builder.setMessage("确定要关闭音乐播放？");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNeutralButton("后台播放", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 既然是后台播放，就是要把当前Activity切换到后台
            }
        });
        builder.setPositiveButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static void main(String args[]) {
        System.out.println("Hello World!");

    }
    /**   Certainly their descriptions of battles are confused. But it is a little upsetting to read in the explanatory notes
     that a certain line describes a fight between a Turkish and a Bulgarian officer on a bridge off which they
     both fall into the river—and then to find that the line consists of the noise of their falling and the weights of the officers:
     “ Pluff! Pluff! A hundred and eighty-five kilograms."
     **/


    /**     This, though it fulfills the laws and requirements of Futurist poetry, can hardly be classed as Literature. All the same,
     no thinking man can refuse to accept their first proposition: that a great change in our emotional life calls for a change of expression.
     The whole question is really this: have we essentially changed?

     19.This passage is mainly	.
     [A]a survey of new approaches to art
     [B]a review of Futurist poetry
     [C]about merits of the Futurist movement
     [D]about laws and requirements of literature

     20.When a novel literary idea appears, people should try to	.
     [A] determine its purposes
     [B] ignore its flaws
     [C] follow the new fashions
     [D] accept the principles

     21.Futurists claim that we must_.
     [A]increase the production of literature
     [B]use poetry to relieve modem stress
     [C]develop new modes of expression
     [D]avoid using adjectives and verbs

     22.The author believes that Futurist poetry is	.
     [A]based on reasonable principles
     [B]new and acceptable to ordinary people
     [C]indicative of basic change in human nature
     [D]more of a transient phenomenon than literature

     ***/


}
