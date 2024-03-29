package com.garlic.events.key;

import lombok.Getter;

@Getter
public enum KeyCode {

    // From glfw3.h
    SPACE(32),
    APOSTROPHE(39), /* ' */
    COMMA(44), /* , */
    MINUS(45), /* - */
    PERIOD(46), /* . */
    SLASH(47), /* / */

    D0(48), /* 0 */
    D1(49), /* 1 */
    D2(50), /* 2 */
    D3(51), /* 3 */
    D4(52), /* 4 */
    D5(53), /* 5 */
    D6(54), /* 6 */
    D7(55), /* 7 */
    D8(56), /* 8 */
    D9(57), /* 9 */

    SEMICOLON(59), /* ; */
    EQUAL(61), /* = */

    A(65),
    B(66),
    C(67),
    D(68),
    E(69),
    F(70),
    G(71),
    H(72),
    I(73),
    J(74),
    K(75),
    L(76),
    M(77),
    N(78),
    O(79),
    P(80),
    Q(81),
    R(82),
    S(83),
    T(84),
    U(85),
    V(86),
    W(87),
    X(88),
    Y(89),
    Z(90),

    LEFTBRACKET(91),  /* [ */
    BACKSLASH(92),  /* \ */
    RIGHTBRACKET(93),  /* ] */
    GRAVEACCENT(96),  /* ` */

    WORLD1(161), /* non-US #1 */
    WORLD2(162), /* non-US #2 */

    /* Function keys */
    ESCAPE(256),
    ENTER(257),
    TAB(258),
    BACKSPACE(259),
    INSERT(260),
    DELETE(261),
    RIGHT(262),
    LEFT(263),
    DOWN(264),
    UP(265),
    PAGEUP(266),
    PAGEDOWN(267),
    HOME(268),
    END(269),
    CAPSLOCK(280),
    SCROLLLOCK(281),
    NUMLOCK(282),
    PRINTSCREEN(283),
    PAUSE(284),
    F1(290),
    F2(291),
    F3(292),
    F4(293),
    F5(294),
    F6(295),
    F7(296),
    F8(297),
    F9(298),
    F10(299),
    F11(300),
    F12(301),
    F13(302),
    F14(303),
    F15(304),
    F16(305),
    F17(306),
    F18(307),
    F19(308),
    F20(309),
    F21(310),
    F22(311),
    F23(312),
    F24(313),
    F25(314),

    /* Keypad */
    KP0(320),
    KP1(321),
    KP2(322),
    KP3(323),
    KP4(324),
    KP5(325),
    KP6(326),
    KP7(327),
    KP8(328),
    KP9(329),
    KP_DECIMAL(330),
    KP_DIVIDE(331),
    KP_MULTIPLY(332),
    KP_SUBTRACT(333),
    KP_ADD(334),
    KP_ENTER(335),
    KP_EQUAL(336),

    LEFT_SHIFT(340),
    LEFT_CONTROL(341),
    LEFT_ALT(342),
    LEFT_SUPER(343),
    RIGHT_SHIFT(344),
    RIGHT_CONTROL(345),
    RIGHT_ALT(346),
    RIGHT_SUPER(347),
    MENU(348),
    UNKNOWN(9999);


    private final int code;

    KeyCode(int code) {
        this.code = code;
    }
}
