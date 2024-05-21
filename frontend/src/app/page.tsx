"use client";

import axios from "axios";
import { useState } from "react";

interface Language {
	id: number;
	name: string;
}

export default function Home() {
	const [language, setLanguage] = useState<Language[]>([]);

	const fetchData = async () => {
		const response = await axios.get<Language[]>(
			"http://localhost:8080/api/v1/languages/",
		);

		setLanguage(response.data);
	};

	return (
		<main>
			<h1>Posts</h1>
			<button type="button" onClick={fetchData}>
				Fetch Data
			</button>
			<ul>
				{language.map((language: Language) => (
					<li key={language.id}>{language.name}</li>
				))}
			</ul>
		</main>
	);
}
