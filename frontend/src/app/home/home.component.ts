import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sort} from "@angular/material/sort";

interface Pet {
  id: number
  name: string;
  petType: string;
  furColor: string;
  country: string;
  imageUrl: string;
  author: string;
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{
  private baseUrl = 'http://localhost:8080/pet';
  username = '';
  pets: Pet[] = [];
  showAddPetForm = false;
  newPet: Pet={
    id: 0,
    name: '',
    petType: '',
    furColor: '',
    country: '',
    imageUrl: '',
    author: ''};
  constructor(private http: HttpClient) {
  }
  getPets(): void {
    this.http.get<any>(`${this.baseUrl}/list`).subscribe(response => {
      const pets: Pet[] = response.data.pets;
      this.pets = pets.filter((pet: Pet) => pet.author === this.username);
    });
  }
  sortData(sort: Sort) {
    const data = this.pets.slice();
    if (!sort.active || sort.direction === '') {
      this.pets = data;
      return;
    }

    this.pets = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name':
          return compare(a.name, b.name, isAsc);
        case 'code':
          return compare(a.id, b.id, isAsc);
        case 'color':
          return compare(a.furColor, b.furColor, isAsc);
        case 'type':
          return compare(a.petType, b.petType, isAsc);
        case 'country':
          return compare(a.country, b.country, isAsc);
        default:
          return 0;
      }
    });
  }
  toggleAddPetForm(): void {
    this.showAddPetForm = !this.showAddPetForm;
  }

  addPet(newPet: Pet | undefined): void {
    const url = `${this.baseUrl}/save`;
    this.newPet.author = this.username;
    const observer = {
      next: (response: any) => {
        console.log('Pet added successfully!');
        this.showAddPetForm = false;
        window.location.reload();
      },
      error: (error: any) => {
        console.log('Error adding pet:', error);
      }
    };
    this.http.post(url, newPet).subscribe(observer);
  }

  editPet(pet: Pet): void {
    this.newPet.id = pet.id;
    this.newPet.author = this.username;
    this.newPet.petType = pet.petType;
    this.newPet.name = pet.name;
    this.newPet.country = pet.country;
    this.newPet.furColor = pet.furColor;
    this.showAddPetForm = !this.showAddPetForm;
  }

  deletePet(pet: Pet): void {
    const url = `${this.baseUrl}/delete/${pet.id}`;
    const observer = {
      next: () => {
        console.log('Pet deleted successfully!');
        window.location.reload();
      },
      error: (error: any) => {
        console.log('Error deleting pet:', error);
      }
    };
    this.http.delete(url).subscribe(observer);
  }
  ngOnInit() {
    const storedUsername = localStorage.getItem('username');
    if (storedUsername !== null) {
      this.username = storedUsername;
    } else {
      this.username = '';
    }
    this.getPets();
  }
}
function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
