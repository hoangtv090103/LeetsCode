import { Navbar } from "@/components/Navbar/Navbar";
import * as React from "react";
import Image from "next/image";
import AuthModal from "@/components/Modals/AuthModal";

export type IAuthPageProps = {};

export default function AuthPage(props: IAuthPageProps) {
	return (
		<div className="bg-gradient-to-b from-gray-600 to-black h-screen relative">
			<div className="max-w-7xl mx-auto">
				<Navbar />
				<div className="flex items-center justify-center h-[calc(100vh-5rem)] pointer-events-none select-none">
					<Image src="/hero.png" alt="Hero" width={800} height={800} />
				</div>
				<AuthModal />
			</div>
		</div>
	);
}
