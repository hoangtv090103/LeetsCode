"use client";

import { RecoilRoot, atom } from "recoil";

type AuthModalState = {
  isOpen: boolean;
  type: "login" | "signup" | "resetPassword";
};

const intialAuthModelState: AuthModalState = {
  isOpen: false,
  type: "login",
};

export const authModalState = atom<AuthModalState>({
  key: "authModelState",
  default: intialAuthModelState,
});

