import { Component, signal, inject } from '@angular/core';
import { HashService } from '../services/hash-service/hash-service';
import { FormsModule } from '@angular/forms';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  private hashService = inject(HashService);
  protected readonly title = signal('Project Two');
  protected data = signal('');
  protected hash = signal('');
  protected showHash = false;

  protected onDataChanged(event: Event) {
    this.showHash = false;
  }

  async onSubmit(event: Event) {
    try {
      const response = await lastValueFrom(this.hashService.hash_request(this.data()));
      this.hash.set(response.toString());
      this.showHash = true;
    } catch (error) {
      alert('Request Failed: ' + error);
    }
  }
}
