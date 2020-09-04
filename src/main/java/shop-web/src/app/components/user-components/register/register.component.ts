import { RegisterService } from './register.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PasswordValidator } from '../../shared/password.validator';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  message: string;
  success: boolean = true;

  registrationForm = this.fb.group(
    {
      firstName: ['', [Validators.required, Validators.maxLength(20)]],
      lastName: ['', [Validators.required, Validators.maxLength(20)]],
      username: [
        '',
        [
          Validators.required,
          Validators.maxLength(20),
          Validators.minLength(6),
          Validators.pattern('^[a-zA-Z0-9_-]+'),
        ],
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.maxLength(32),
          Validators.minLength(8),
          Validators.pattern(
            '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{4,}$'
          ),
        ],
      ],
      confirmPassword: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone: [
        '',
        [
          Validators.required,
          Validators.pattern('^(09|08|07|01|03)+([0-9]{8}$)'),
        ],
      ],
    },
    { validators: PasswordValidator }
  );

  get username() {
    return this.registrationForm.get('username');
  }

  get firstName() {
    return this.registrationForm.get('firstName');
  }

  get lastName() {
    return this.registrationForm.get('lastName');
  }

  get password() {
    return this.registrationForm.get('password');
  }
  get email() {
    return this.registrationForm.get('email');
  }
  get phone() {
    return this.registrationForm.get('phone');
  }
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: RegisterService
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    var x = {
      email: this.registrationForm.get('email').value,
      firstName: this.registrationForm.get('firstName').value,
      lastName: this.registrationForm.get('lastName').value,
      password: this.registrationForm.get('password').value,
      phone: this.registrationForm.get('phone').value,
      roles: ['USER'],
      username: this.registrationForm.get('username').value,
    };
    this.service.register(x).subscribe(
      res => {
        if (res.data != null && res.success) {
          this.router.navigateByUrl('/login');
        }
      },
      err => {
        this.message = err;
        this.success = false;
      }
    );
  }
}
