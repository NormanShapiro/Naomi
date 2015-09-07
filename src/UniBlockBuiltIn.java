/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.naomi.regex;

/**
     This Enum defines a set of constants, each of which specifies a
     Unicode "block" of characters to be matched (see below).  Each
     of these Enum constants can be used to instantiate a {@link BuiltInCharClass}
     that matches any of the characters in the corresponding Unicode block.
     For example,
<p>
<pre>
      Pattern GreekCoptic =
		new BuiltInCharClass(UniBlockBuiltIn.Greek_and_Coptic);
</pre>
     matches (on each repetition) one Greek or Coptic character.

@see <a href="http://en.wikipedia.org/wiki/Unicode_block"> Unicode block</a>

*/

public enum UniBlockBuiltIn implements BuiltInInterface
{
//(
Aegean_numbers(null),
Alchemical_symbols(null),
Alphabetic_presentation_forms(null),
Ancient_greek_musical_notation(null),
Ancient_greek_numbers(null),
Ancient_symbols(null),
Arabic(null),
Arabic_presentation_forms_a(null),
Arabic_presentation_forms_b(null),
Arabic_supplement(null),
Armenian(null),
Arrows(null),
Avestan(null),
Balinese(null),
Bamum(null),
Bamum_supplement(null),
Basic_latin(null),
Batak(null),
Bengali(null),
Block_elements(null),
Bopomofo(null),
Bopomofo_extended(null),
Box_drawing(null),
Brahmi(null),
Braille_patterns(null),
Buginese(null),
Buhid(null),
Byzantine_musical_symbols(null),
Carian(null),
Cham(null),
Cherokee(null),
Cjk_compatibility(null),
Cjk_compatibility_forms(null),
Cjk_compatibility_ideographs(null),
Cjk_compatibility_ideographs_supplement(null),
Cjk_radicals_supplement(null),
Cjk_strokes(null),
Cjk_symbols_and_punctuation(null),
Cjk_unified_ideographs(null),
Cjk_unified_ideographs_extension_a(null),
Cjk_unified_ideographs_extension_b(null),
Cjk_unified_ideographs_extension_c(null),
Cjk_unified_ideographs_extension_d(null),
Combining_diacritical_marks(null),
Combining_diacritical_marks_supplement(null),
Combining_half_marks(null),
Combining_marks_for_symbols(null),
Common_indic_number_forms(null),
Control_pictures(null),
Coptic(null),
Counting_rod_numerals(null),
Cuneiform(null),
Cuneiform_numbers_and_punctuation(null),
Currency_symbols(null),
Cypriot_syllabary(null),
Cyrillic(null),
Cyrillic_extended_a(null),
Cyrillic_extended_b(null),
Cyrillic_supplementary(null),
Deseret(null),
Devanagari(null),
Devanagari_extended(null),
Dingbats(null),
Domino_tiles(null),
Egyptian_hieroglyphs(null),
Emoticons(null),
Enclosed_alphanumeric_supplement(null),
Enclosed_alphanumerics(null),
Enclosed_cjk_letters_and_months(null),
Enclosed_ideographic_supplement(null),
Ethiopic(null),
Ethiopic_extended(null),
Ethiopic_extended_a(null),
Ethiopic_supplement(null),
General_punctuation(null),
Geometric_shapes(null),
Georgian(null),
Georgian_supplement(null),
Glagolitic(null),
Gothic(null),
Greek(null),
Greek_extended(null),
Gujarati(null),
Gurmukhi(null),
Halfwidth_and_fullwidth_forms(null),
Hangul_compatibility_jamo(null),
Hangul_jamo(null),
Hangul_jamo_extended_a(null),
Hangul_jamo_extended_b(null),
Hangul_syllables(null),
Hanunoo(null),
Hebrew(null),
High_private_use_surrogates(null),
High_surrogates(null),
Hiragana(null),
Ideographic_description_characters(null),
Imperial_aramaic(null),
Inscriptional_pahlavi(null),
Inscriptional_parthian(null),
Ipa_extensions(null),
Javanese(null),
Kaithi(null),
Kana_supplement(null),
Kanbun(null),
Kangxi_radicals(null),
Kannada(null),
Katakana(null),
Katakana_phonetic_extensions(null),
Kayah_li(null),
Kharoshthi(null),
Khmer(null),
Khmer_symbols(null),
Lao(null),
Latin_1_supplement(null),
Latin_extended_a(null),
Latin_extended_additional(null),
Latin_extended_b(null),
Latin_extended_c(null),
Latin_extended_d(null),
Lepcha(null),
Letterlike_symbols(null),
Limbu(null),
Linear_b_ideograms(null),
Linear_b_syllabary(null),
Lisu(null),
Low_surrogates(null),
Lycian(null),
Lydian(null),
Mahjong_tiles(null),
Malayalam(null),
Mandaic(null),
Mathematical_alphanumeric_symbols(null),
Mathematical_operators(null),
Meetei_mayek(null),
Miscellaneous_mathematical_symbols_a(null),
Miscellaneous_mathematical_symbols_b(null),
Miscellaneous_symbols(null),
Miscellaneous_symbols_and_arrows(null),
Miscellaneous_symbols_and_pictographs(null),
Miscellaneous_technical(null),
Modifier_tone_letters(null),
Mongolian(null),
Musical_symbols(null),
Myanmar(null),
Myanmar_extended_a(null),
New_tai_lue(null),
Nko(null),
Number_forms(null),
Ogham(null),
Ol_chiki(null),
Old_italic(null),
Old_persian(null),
Old_south_arabian(null),
Old_turkic(null),
Optical_character_recognition(null),
Oriya(null),
Osmanya(null),
Phags_pa(null),
Phaistos_disc(null),
Phoenician(null),
Phonetic_extensions(null),
Phonetic_extensions_supplement(null),
Playing_cards(null),
Private_use_area(null),
Rejang(null),
Rumi_numeral_symbols(null),
Runic(null),
Samaritan(null),
Saurashtra(null),
Shavian(null),
Sinhala(null),
Small_form_variants(null),
Spacing_modifier_letters(null),
Specials(null),
Sundanese(null),
Superscripts_and_subscripts(null),
Supplemental_arrows_a(null),
Supplemental_arrows_b(null),
Supplemental_mathematical_operators(null),
Supplemental_punctuation(null),
Supplementary_private_use_area_a(null),
Supplementary_private_use_area_b(null),
Surrogates_area(null),
Syloti_nagri(null),
Syriac(null),
Tagalog(null),
Tagbanwa(null),
Tags(null),
Tai_le(null),
Tai_tham(null),
Tai_viet(null),
Tai_xuan_jing_symbols(null),
Tamil(null),
Telugu(null),
Thaana(null),
Thai(null),
Tibetan(null),
Tifinagh(null),
Transport_and_map_symbols(null),
Ugaritic(null),
Unified_canadian_aboriginal_syllabics(null),
Unified_canadian_aboriginal_syllabics_extended(null),
Vai(null),
Variation_selectors(null),
Variation_selectors_supplement(null),
Vedic_extensions(null),
Vertical_forms(null),
Yi_radicals(null),
Yi_syllables(null),
Yijing_hexagram_symbols(null),

//)
  ;
  private String string;

  UniBlockBuiltIn(String string)
  {
     this.string=string;
  }

  public CharSequence getPreRegularExpression()
  {
     if(string == null)
        string="\\p{In"+name()+"}";
     return string;
  }

  public String toString() {return getClass().getSimpleName()+"."+name();}
}
