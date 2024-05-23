"use client";

import { Navbar } from "@/components/Navbar/Navbar";
import * as React from "react";
import Image from "next/image";
import AuthModal from "@/components/Modals/AuthModal";
import { RecoilRoot, useRecoilValue } from "recoil";
import { authModalState } from "@/atoms/authModalAtom";

export type IAuthPageProps = {};

export default function AuthPage(props: IAuthPageProps) {
  const authModal = useRecoilValue(authModalState);
  console.log(authModal.isOpen);
  return (
    <div className="bg-gradient-to-b from-gray-600 to-black h-screen relative">
      <div className="max-w-7xl mx-auto">
        <Navbar />
        <div className="flex items-center justify-center h-[calc(100vh-5rem)] pointer-events-none select-none">
          <Image src="/hero.png" alt="Hero" width={800} height={800} />
        </div>
        {authModal.isOpen && <AuthModal />}
      </div>
    </div>
  );
}
