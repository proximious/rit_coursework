"""
ALex Iacob
ai9388@rit.edu
Project 1: HTML Builder
file: wizard_mode.py
"""

# imports
from dataclasses import dataclass

import write_fonts
from colors import COLORS


######## declaring dataclasses ########
@dataclass
class Html:
    head: 'Head'
    body: 'Body'


@dataclass
class Head:
    title: str
    style: str


@dataclass
class Body:
    headings: list
    paragraphs: list


@dataclass
class Paragraph:
    content: str
    image: list


@dataclass
class Image:
    source: str
    width: int


######## declaring global functions ########
def make_style_into_string():
    """
    makes the entire stylesheet into a single string
    :return: one massive string
    """
    STYLE_STRING = ''
    with open('style_template.txt', "r")as file:
        for line in file:
            STYLE_STRING += line
    return STYLE_STRING


def replace_tag(string, tag, replacement):
    """
    replaces any instance of the tag and replaces it with the replacement
    :param string: the string created from above function
    :param tag: the tag we are searching for
    :param replacement: the replacement for 'tag'
    :return: the edited string
    """
    if tag in string:
        new_s = string.replace(tag, replacement)
    string = new_s
    return string


def verify_hexadecimal(hexa):
    """
    verifies whether the given hexadecimal is a valid hexadecimal
    :param hexa: given hexadecimal
    :return:
    """
    if hexa in COLORS:
        return True
    else:
        illegal_characters = ['g', 'h', 'i', 'j', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                              'z']
        hexa_list = []

        for char in hexa:
            hexa_list.append(char)

        for i in hexa_list:
            if hexa_list[0] != '#':
                return False
            elif len(hexa_list) > 7:
                return False
            elif i in illegal_characters:
                return False
        # valid hex code
        return True


######## declaring prompting functions ########
def prompt_title():
    """
    asks the user for the title
    :return: the title in the title tag and h1 tag
    """
    title_content = input('What is the title of your website?\n')
    return create_title(title_content), create_h1(title_content)


def create_title(title_content):
    """
    creates the title tag
    :param title_content: content being placed inside of the title
    :return: the title tag
    """
    title_start = '<title>'
    title_end = '</title>'
    return title_start + title_content + title_end


def create_h1(title_content):
    """
    creates the h1 tag
    :param title_content: content being placed inside of the h1
    :return: the h1 tag
    """
    h1_start = '<h1>'
    h1_end = '</h1>'
    return h1_start + title_content + h1_end


def prompt_back_color(STYLE_STRING):
    """
    asks the user for what background color they want
    :param STYLE_STRING: the stylesheet
    :return: the background color
    """
    back_color_content = input("What color would you like the background to be?\n"
                               "Write the color's name or hexadecimal value as '#XXXXXX'.\n")
    if verify_hexadecimal(back_color_content):
        return replace_tag(STYLE_STRING, '@BACKCOLOR', back_color_content)
    else:
        print('Invalid code, try again.')
        return prompt_back_color(STYLE_STRING)


def prompt_font_style(STYLE_STRING):
    """
    asks the user for the font style they want
    also opens a turtle graphics window to show the possible fonts
    :param STYLE_STRING: the stylesheet
    :return: the font style
    """
    print("What font would you like to use? Type 0-6 once your choice has been made.\n"
          " 0: Arial \n 1: Comic Sans \n 2: Lucida Grande \n 3: Tahoma \n 4: Verdana \n"
          " 5: Helvetica \n 6: Times New Roman")
    write_fonts.main()
    font_style_content = input()

    if font_style_content == '0':
        font_choice = 'Arial'
    elif font_style_content == '1':
        font_choice = 'Comic Sans MS'
    elif font_style_content == '2':
        font_choice = 'Lucida Grande'
    elif font_style_content == '3':
        font_choice = 'Tahoma'
    elif font_style_content == '4':
        font_choice = 'Verdana'
    elif font_style_content == '5':
        font_choice = 'Helvetica'
    elif font_style_content == '6':
        font_choice = 'Times New Roman'
    else:
        print('Value not acceptable, please try again')
        return prompt_font_style(STYLE_STRING)

    print("You have chosen", font_choice)
    return replace_tag(STYLE_STRING, '@FONTSTYLE', font_choice)


def prompt_paragraph_color(STYLE_STRING):
    """
    asks the user for the paragraph color
    :param STYLE_STRING: the stylesheet
    :return: the paragraph color
    """
    paragraph_color_content = input("What color would you like the paragraphs to be?\n"
                                    "Write the color's name or hexadecimal value as '#XXXXXX'.\n")
    if verify_hexadecimal(paragraph_color_content):
        return replace_tag(STYLE_STRING, '@FONTCOLOR', paragraph_color_content)
    else:
        print('Invalid code, try again.')
        return prompt_paragraph_color(STYLE_STRING)


def prompt_heading_color(STYLE_STRING):
    """
    asks the user for the heading color
    :param STYLE_STRING: the stylesheet
    :return: the heading color
    """
    heading_color_content = input("What color would you like the headings to be?\n"
                                  "Write the color's name or hexadecimal value as '#XXXXXX'.\n")
    if verify_hexadecimal(heading_color_content):
        return replace_tag(STYLE_STRING, '@HEADCOLOR', heading_color_content)
    else:
        print('Invalid code, try again.')
        return prompt_paragraph_color(STYLE_STRING)


def prompt_style(STYLE_STRING):
    """
    combines each of the edited stylesheet aspects
    :param STYLE_STRING: the stylesheet
    :return: the fully edited stylesheet
    """
    print('You will now choose a background color.')
    STYLE_STRING = prompt_back_color(STYLE_STRING)
    print('You will now chose a font style. Close the turtle graphics window to make your choice.')
    STYLE_STRING = prompt_font_style(STYLE_STRING)
    print('You will now choose the color of the paragraphs.')
    STYLE_STRING = prompt_paragraph_color(STYLE_STRING)
    print('You will now choose the color of the headings.')
    STYLE_STRING = prompt_heading_color(STYLE_STRING)
    return STYLE_STRING


def prompt_headings():
    """
    asks the user for the possible headings
    :return: the html tag with the heading content
    """
    print('You will now choose the content for the heading.')
    heading_content = input('What would you like the heading to say?\n')
    heading_start = '<h2>'
    heading_end = '</h2>\n'
    return heading_start + heading_content + heading_end


def prompt_paragraphs():
    """
    asks the user for the possible paragraphs
    :return: the html tag with the paragraphs content
    """
    print('You will now write content for the paragraph.')
    paragraph_content = input('What would you like the paragraph to say? (Single line)\n')
    paragraph_start = '<p>'
    paragraph_end = '</p>\n'
    return paragraph_start + paragraph_content + paragraph_end


def prompt_images():
    """
    asks the user for which images they would want
    :return:
    """
    print('You will now choose some images for your website.')
    image_source = input('Write the directory for the image you would like to add.\n')
    image_start = '<img src = \"'
    image_end = '\" alt ="" class = "center">\n'
    return image_start + image_source + image_end


def html_create():
    """
    combines previous functions into one
    :return: the html file
    """
    html = '<!DOCTYPE html>\n<html lang="en">\n<head>\n<meta charset="UTF-8">\n<meta name="viewport" ' \
           'content="width=device-width, initial-scale=1.0">\n<meta http-equiv="X-UA-Compatible" content="ie=edge">\n'
    STYLE_STRING = make_style_into_string()
    title = prompt_title()
    STYLE_STRING = prompt_style(STYLE_STRING)

    ##############
    headings = prompt_headings()
    paragraphs = prompt_paragraphs()
    images = prompt_images()
    head_end_body = '</head>\n<body>'
    html_ending = '</body>\n</html>'

    html_with_style = html + STYLE_STRING
    total = html_with_style + title[0] + head_end_body + title[1] + headings + paragraphs + images

    paragraph_bool = False
    image_bool = False

    while paragraph_bool is False:
        image_bool = False
        para_choice = input('Would you like another paragraph?[yes]')
        if para_choice == 'yes' or para_choice == '':
            new_h = prompt_headings()
            new_p = prompt_paragraphs()
            total += new_h
            total += new_p
            paragraph_bool = True

        while image_bool is False:
            img_choice = input('Would you like another image?[yes]')
            if img_choice == 'yes' or img_choice == '':
                new_i = prompt_images()
                total += new_i
                image_bool = True
            else:
                total += html_ending
                return total


def create_file(info):
    outfile = open('index.html', "w")
    for line in info:
        outfile.write(line)


def main():
    file = html_create()
    create_file(file)
    print('Your new html file is saved under index.html in your directory.')


if __name__ == '__main__':
    main()
