This project uses [README Driven Development so...](http://tom.preston-werner.com/2010/08/23/readme-driven-development.html) there is nothing here yet except for this README!

# Jenny

Jenny generates Java Projects from templates, so that you can get started faster.

# Prerequisites

* Java 8 JRE
* JAVA_HOME environment variable pointing at the installation directory of the JRE

# Installation

* Download Jenny from TODO.
* Unzip and add the extracted directory to your PATH.
* Point the environment variable JENNY_HOME to where you extracted Jenny.
* On a command line, run `jen` to check that installation was successful.

# Usage

Add a new repository of templates. "main" is just a name to refer to a repository. It can be
anything you want but since you'll need to type it every time, it is worth choosing something
short. The url can be any valid git repository containing templates.

    jen add main https://github.com/rupert654/jennyTemplateRepository

See all the templates that are repositories you have added.

    jen list

Create a new project, relative to your current directory, from the specified template.

    jen new main example

You will be prompted if the template requires any more information from you.

# Write Templates

Templates are organised and released as git repositories. To make your repository compatible with
Jenny, it may only contain top-level directories that are templates. The name of the template is
the name of the directory (and is what will need to be typed every time your template is used, so
keep it short!). Your repository may contain any number of other files at the top-level (e.g. a
licence or README) as Jenny will ignore these.

Each template consists of any number of files and directories which will be directly copied when a
new project is created from it.

In addition, you must have a template.xml file at the top-level which allows you to define
meta-data about your template, including any parameters that you want to request when it used. You
will be able to use these parameters in both the names of the files and directories that make up
your template, as well as in the content of those files.
