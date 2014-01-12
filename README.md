Razi
====

**Razi** is a new scientific chemistry library for java. 

I started this project in December, 20, 2013 to make a free new open-source scientific library for java.

Installation
====
To use this library you can download the `Razi.jar` which exists in dist directory and import it into your project. also you can manipulate sources and compile it.

# What can RAZI do ? #
By now RAZI can be used to convert chemical table formats.

## SMILES Parser ##
Use `com.razi.formats.smiles.Reader` for converting a SMILES string to Molecule model. 

    Reader sr = new Reader();
    sr.set("C(=O)O");
    sr.process();
    
    Molecule mol = sr.get();

## Descriptors ##
By now you can use `com.razi.descriptor.molecular.CountDescriptor` to count something in a molecule. For exmaple :

    cd.countAtoms(); // Number of all atoms
    cd.countBonds(); // Number of all bonds
    cd.countBondsByOrder(2); // Number of bonds with order 2
	cd.cd.countAtomsByElement(element); // Number of all atoms which are the same with given `element`

# What does *Razi* mean ? #
[Razi](http://en.wikipedia.org/wiki/Muhammad_ibn_Zakariya_al-Razi) was a Persian alchemist and chemist.