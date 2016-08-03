package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {
	
	public static Scanner sc = new Scanner(System.in);
	public static int reponse;
	public static int i;
	public static String[][] listePizza = {{"PEP","Pépéroni","12,50"},
									{"MAR","Margherita","14,00"},
									{"REI","La Reine","11,50"},
									{"FRO","La 4 fromages","12,00"},
									{"CAN","La cannibale","12,50"},
									{"SAV","La savoyarde","13,00"},
									{"ORI","L'orientale","13,50"},
									{"IND","L'indienne","14,00"}};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean fin = false;
		
		menu();
		reponse = sc.nextInt();
			
		do{	// tant que le signal de sortie n'est pas donné, on continue d'afficher le menu
			switch (reponse) {
				case 1:	// affichage de la liste des pizzas
					System.out.println("Liste des pizzas");
					System.out.println(" ");
					afficherPizzas();
					menu();
					reponse = sc.nextInt();
					break;
				case 2:	// ajout d'une nouvelle pizza
					System.out.println("Ajout d'une nouvelle pizza");
					System.out.println(" ");
					listePizza = ajouterPizza();	// on remplace l'ancien tableau par le nouveau
					menu();
					reponse = sc.nextInt();
					break;
				case 3:	// mis à jour des informations d'une pizza
					System.out.println("Mise à jour d'une pizza");
					System.out.println(" ");
					majPizza();
					menu();
					reponse = sc.nextInt();
					break;
				case 4:	// suppression d'une pizza
					System.out.println("Suppression d'une pizza");
					System.out.println(" ");
					listePizza = supprimerPizza();	// on remplace l'ancien tableau par le nouveau
					menu();
					reponse = sc.nextInt();
					break;
				case 99:	// sortie du programme (signal de sortie)
					System.out.println("Au revoir :(");
					fin = true;	// signal de sortie
					break;
				default:	// pas de réponse convenable, on réaffiche le menu
					menu();
					reponse = sc.nextInt();
					break;
			}
		}while(fin == false);
		
		sc.close();
	}
	
	public static void menu() {
		
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
		System.out.println(" ");		
	}
	
	public static void afficherPizzas() {
		
		for(i=0; i < listePizza.length; i++){	// parcourt et affichage de la liste
			System.out.println(i + ". " + listePizza[i][0] + " -> " + listePizza[i][1] + " (" + listePizza[i][2] + ")");
		}
		System.out.println(" ");
	}
	
	public static String[][] ajouterPizza() {
		System.out.println("Veuillez saisir le code");
		String code = sc.next();
		System.out.println("Veuillez saisir le nom (sans espaces)");
		String nom = sc.next();
		System.out.println("Veuillez saisir le prix");
		String prix = sc.next();
		
		String listeProvisoire[][] = new String[listePizza.length + 1][3];	// tableau provisoire incrémenté de 1
		for(i=0; i < listePizza.length; i++) {	// on recopie les informations du tableau existant
			listeProvisoire[i][0] = listePizza[i][0];
			listeProvisoire[i][1] = listePizza[i][1];
			listeProvisoire[i][2] = listePizza[i][2];
		}
		// on ajoute la nouvelle pizza
		listeProvisoire[listePizza.length][0] = code;
		listeProvisoire[listePizza.length][1] = nom;
		listeProvisoire[listePizza.length][2] = prix;
		return listeProvisoire;
	}
	
	public static void majPizza() {
		afficherPizzas();	// on affiche la liste
		System.out.println("Veuillez choisir la pizza à modifier");
		System.out.println("(99 pour abandonner)");
		int index = sc.nextInt();	// on utilise l'index du tableau pour la sélection
		if(index != 99 && index < listePizza.length) {
			System.out.println("Veuillez saisir le code");
			String code = sc.next();
			System.out.println("Veuillez saisir le nom (sans espaces)");
			String nom = sc.next();
			System.out.println("Veuillez saisir le prix");
			String prix = sc.next();
			// on mets à jour les informations de la pizza modifiée
			listePizza[index][0] = code;
			listePizza[index][1] = nom;
			listePizza[index][2] = prix;
		}
	}

	public static String[][] supprimerPizza() {
		int newIndex;
		
		afficherPizzas();	// on affiche la liste
		System.out.println("Veuillez choisir la pizza à supprimer");
		System.out.println("(99 pour abandonner)");
		int index = sc.nextInt();	// on utilise l'index du tableau pour la sélection
		if(index != 99 && index < listePizza.length) {	// on parcourt la liste
			String listeProvisoire[][] = new String[listePizza.length - 1][3];	// tableau provisoire décrémenté de 1
			
			for(i=0; i < listePizza.length - 1; i++) {
				if(i>=index) { newIndex = i + 1; }	// on modifie l'index pour passer la pizza à modifier
				else { newIndex = i; }
				// on copie les informations de l'ancienne liste sauf la pizza supprimée
				listeProvisoire[i][0] = listePizza[newIndex][0];
				listeProvisoire[i][1] = listePizza[newIndex][1];
				listeProvisoire[i][2] = listePizza[newIndex][2];
			}
			
			return listeProvisoire;
		}
		else {
			return listePizza;	// si aucune pizza n'est supprimée, on retourne la liste originale
		}
	}
}
